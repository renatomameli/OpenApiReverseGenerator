import exception.UnsupportedClassTypeException
import model.Property
import model.Schema
import model.Type
import utils.ReflectionUtils
import java.lang.reflect.Modifier
import java.util.*

class ClassToSchemaConverter {

    fun convertJavaClassToSchema(clazz: Class<*>): Schema {
        return Schema(mapToType(clazz), clazz.name)
    }

    private fun mapToType(clazz: Class<*>): Type {
        if (clazz.isInterface) {
            throw UnsupportedClassTypeException()
        } else if (clazz.isEnum) {
            return Type.STRING
        } else if (clazz.isAnnotation) {
            throw UnsupportedClassTypeException()
        }
        return Type.OBJECT
    }

    private fun mapToProperties(clazz: Class<*>): Map<String, Property> {
        val properties = mutableMapOf<String, Property>()
        for (field in clazz.declaredFields) {
            if (Modifier.isStatic(field.modifiers)) {
                continue
            }
            val type = ReflectionUtils.getFieldType(field)
            properties[field.name] = Property(mapFieldTypeToType(type), null)
        }
        return emptyMap()
    }

    private fun mapFieldTypeToType(type: String): Type {
        val typeUppercase = type.uppercase(Locale.getDefault())
        if (typeUppercase == Type.STRING.toString()) {
            return Type.STRING
        } else {
            return Type.OBJECT
        }
        // TODO Add other cases
    }
}