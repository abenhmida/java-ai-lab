package com.krizaldis.ai.core.embedding

import com.krizaldis.ai.core.vector.Vector
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InMemoryVectorStoreTest {
    @Test
    fun `test extract document`() {
        val vectorStore = InMemoryVectorStore()

        val kafka =
            VectorDocument(
                id = "kafka",
                content = "Kafka partitions preserve ordering",
                vector =
                    Vector(
                        listOf(1.0, 0.0),
                    ),
            )

        val java =
            VectorDocument(
                id = "java",
                content = "JVM garbage collection manages memory",
                vector =
                    Vector(
                        listOf(0.0, 1.0),
                    ),
            )

        vectorStore.apply {
            add(java)
            add(kafka)
        }

        val query =
            Vector(
                listOf(0.9, 0.1),
            )

        val result =
            vectorStore.search(
                VectorSearchRequest(
                    query = query,
                    topK = 1,
                    filter = VectorSearchFilter(),
                ),
            )

        assertEquals(1, result.size)
        assertEquals("kafka", result[0].document.id)
    }

    @Test
    fun `returns top k documents`() {
        val store =
            InMemoryVectorStore()

        store.add(
            VectorDocument(
                id = "A",
                content = "A",
                vector =
                    Vector(
                        listOf(1.0, 0.0),
                    ),
            ),
        )

        store.add(
            VectorDocument(
                id = "B",
                content = "B",
                vector =
                    Vector(
                        listOf(0.0, 1.0),
                    ),
            ),
        )

        store.add(
            VectorDocument(
                id = "C",
                content = "C",
                vector =
                    Vector(
                        listOf(0.8, 0.2),
                    ),
            ),
        )

        val query =
            Vector(
                listOf(1.0, 0.0),
            )

        val results =
            store.search(
                VectorSearchRequest(
                    query = query,
                    topK = 2,
                    filter = VectorSearchFilter(),
                ),
            )

        assertEquals(
            listOf("A", "C"),
            results.map {
                it.document.id
            },
        )
    }

    @Test
    fun filter() {
        val store =
            InMemoryVectorStore()

        store.add(
            VectorDocument(
                id = "a",
                content = "Kafka partitions",
                vector =
                    Vector(
                        listOf(1.0, 0.0),
                    ),
                metadata =
                    mapOf(
                        "tenant" to "customer-a",
                    ),
            ),
        )

        store.add(
            VectorDocument(
                id = "b",
                content = "Kafka partitions",
                vector =
                    Vector(
                        listOf(1.0, 0.0),
                    ),
                metadata =
                    mapOf(
                        "tenant" to "customer-b",
                    ),
            ),
        )

        val request =
            VectorSearchRequest(
                query =
                    Vector(
                        listOf(1.0, 0.0),
                    ),
                topK = 10,
                filter =
                    VectorSearchFilter(
                        metadata =
                            mapOf(
                                "tenant" to "customer-a",
                            ),
                    ),
            )

        val results = store.search(request)

        assertEquals(
            listOf("a"),
            results.map {
                it.document.id
            },
        )
    }
}
