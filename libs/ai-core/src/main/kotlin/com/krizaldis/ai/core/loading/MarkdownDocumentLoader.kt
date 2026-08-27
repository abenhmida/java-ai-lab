package com.krizaldis.ai.core.loading

import com.krizaldis.ai.core.embedding.Document
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.extension

class MarkdownDocumentLoader(
    private val root: Path,
) : DocumentLoader {
    override fun load(): List<Document> =
        Files
            .walk(root)
            .filter { Files.isRegularFile(it) }
            .filter { it.extension == "md" }
            .map { path ->
                Document(
                    id = path.toString(),
                    content = Files.readString(path),
                    metadata =
                        mapOf(
                            "source" to path.toString(),
                        ),
                )
            }.toList()
}
