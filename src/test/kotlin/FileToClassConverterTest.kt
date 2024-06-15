import org.junit.jupiter.api.Test
import java.net.URL
import java.nio.file.Paths
import kotlin.test.assertTrue


class FileToClassConverterTest {

    @Test
    fun convertFileToClass() {
        val classConverter = FileToClassConverter()
        val resource: URL? = FileToClassConverterTest::class.java.getResource("/TestClass.java")
        val file = Paths.get(resource!!.toURI()).toFile()
        val clazz = classConverter.convertFileToClass(file)
        assertTrue { clazz.declaredFields.isNotEmpty()  }
    }
}
