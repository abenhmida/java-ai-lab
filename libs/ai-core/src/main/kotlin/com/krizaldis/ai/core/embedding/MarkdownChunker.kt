package com.krizaldis.ai.core.embedding

class MarkdownChunker(
    private val maxCharacters: Int = 1500,
) : DocumentChunker {
    override fun chunk(document: Document): List<DocumentChunk> {
        val paragraphs =
            document
                .content
                .split(Regex("\\n\\s*\\n"))

        val chunks = mutableListOf<DocumentChunk>()

        var current = StringBuilder()

        var index = 0

        for (paragraph in paragraphs) {
            if (
                current.length + paragraph.length > maxCharacters &&
                current.isNotEmpty()
            ) {
                chunks +=
                    createChunk(
                        document,
                        index++,
                        current.toString(),
                    )

                current = StringBuilder()
            }

            current
                .append(paragraph)
                .append("\n\n")
        }

        if (current.isNotEmpty()) {
            chunks +=
                createChunk(
                    document,
                    index,
                    current.toString(),
                )
        }

        return chunks
    }

    private fun createChunk(
        document: Document,
        index: Int,
        content: String,
    ): DocumentChunk =
        DocumentChunk(
            id =
                "${document.id}:$index",
            documentId =
                document.id,
            documentVersion = 1,
            chunkIndex = index,
            content = content.trim(),
            metadata = document.metadata,
        )
}
