import kotlin.math.max
import kotlin.reflect.KClass

class Table (private val columns: MutableList<Column>, private val rows: MutableList<Row>) {

    fun render() {
        val valuesMaxLen = mutableMapOf<String, Int>()
        for (cols in columns) {
            valuesMaxLen[cols.name ?: ""] = max(valuesMaxLen[cols.name] ?: 0, cols.name!!.length)
        }
        for (row in rows) {
            for (cell in row.values) {
                valuesMaxLen[cell.columnName] = max(cell.value.toString().length, valuesMaxLen[cell.columnName] ?: 0)
            }
        }

        val header = buildString {
            append("+")
            valuesMaxLen.forEach {
                append("-".repeat(it.value + 2))
                append("+")
            }
            append("\n")
            append("|")
            columns.forEach { column ->
                append(" ${column.name!!.padEnd(valuesMaxLen[column.name] ?: 0)} |")
            }
            append("\n")
            append("+")
            valuesMaxLen.forEach {
                append("-".repeat(it.value + 2))
                append("+")
            }
        }

        val dataRows = buildString {
            rows.forEach { row ->
                append("|")
                row.values.forEach { cell ->
                    append(" ${cell.value.toString().padEnd(valuesMaxLen[cell.columnName] ?: 0)} |")
                }
                append("\n")
            }
            append("+")
            valuesMaxLen.forEach {
                append("-".repeat(it.value + 2))
                append("+")
            }
        }

        println(header)
        println(dataRows)
    }

}

class Column (val name: String? = null, val type: KClass<*>? = null) {
    override fun toString(): String {
        return "$name, $type"
    }
}

class Row (private val index: Int, val values: List<Cell>) {
    override fun toString(): String {
        return "id=$index, vals=${values}"
    }
}

class Cell (val columnName: String, val value: Any) {
    override fun toString(): String {
        return "$value"
    }
}