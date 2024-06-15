import model.Schema
import java.io.File

class OpenApiReverseGenerator {
    fun convert(file: File): Schema {
        val classToSchemaConverter = ClassToSchemaConverter()
        val fileToClassConverter = FileToClassConverter()
        return classToSchemaConverter.convertJavaClassToSchema(fileToClassConverter.convertFileToClass(file))
    }
}