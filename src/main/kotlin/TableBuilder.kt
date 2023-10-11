import kotlin.reflect.KClass

class TableBuilder {
    private val table: MutableList<Column> = mutableListOf()
    private val rows: MutableList<Row> = mutableListOf()

    fun build(): Table {
        return Table(table, rows)
    }

    fun column(name: String, type: KClass<*>) = table.add(Column(name = name, type = type))

    fun row(init: RowBuilder.() -> Unit) {
        val tempRow = RowBuilder().apply(init).build()
        if (tempRow.values.size != table.size) {
            return
        }
        for ((idx, values) in tempRow.values.withIndex()) {
            if (values.columnName != table[idx].name || values.value::class != table[idx].type) {
                return
            }
        }
        rows.add(tempRow)
    }

}

class RowBuilder {
    private val row: MutableList<Cell> = mutableListOf()

    companion object {
        var rowId = 0
    }

    fun build(): Row = Row(rowId++, row)

    fun cell(name: String, value: Any) = row.add(Cell(name, value))
}