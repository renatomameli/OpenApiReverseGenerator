package utils

import java.lang.reflect.Field
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

object ReflectionUtils {
    fun getFieldType(field: Field): String {
        val genericType: Type = field.genericType

        return if (genericType is ParameterizedType) {
            val typeName = genericType.rawType.typeName
            val typeArguments = genericType.actualTypeArguments.joinToString(", ") { it.typeName }
            "$typeName<$typeArguments>"
        } else {
            field.type.simpleName
        }
    }
}