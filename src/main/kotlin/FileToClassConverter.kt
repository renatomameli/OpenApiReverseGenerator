import java.io.File
import java.net.URLClassLoader
import javax.tools.JavaCompiler
import javax.tools.ToolProvider

class FileToClassConverter {
    fun convertFileToClass(javaFile: File): Class<*> {
        val compiler: JavaCompiler = ToolProvider.getSystemJavaCompiler()
        val tempDir = File("tempDir")
        tempDir.mkdirs()

        val compilationUnits = listOf(javaFile.absolutePath)
        val fileManager = compiler.getStandardFileManager(null, null, null)
        val options = listOf("-d", tempDir.absolutePath)

        val compilationTask = compiler.getTask(null, fileManager, null, options, null, fileManager.getJavaFileObjectsFromStrings(compilationUnits))

        if (!compilationTask.call()) {
            throw RuntimeException()
        }

        val classLoader = URLClassLoader(arrayOf(tempDir.toURI().toURL()), ClassLoader.getSystemClassLoader())
        return classLoader.loadClass(javaFile.nameWithoutExtension)
    }
}