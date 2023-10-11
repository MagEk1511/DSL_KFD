fun table(init: TableBuilder.() -> Unit): Table = TableBuilder().apply(init).build()

fun main() {
    table {
        column("ID", Int::class)
        column("Name", String::class)
        column("Age", Int::class)
        column("Occupation", String::class)

        row {
            cell("ID", 1)
            cell("Name", "Alice")
            cell("Age", 25)
            cell("Occupation", "IT")
        }

        row {
            cell("ID", 2)
            cell("Name", "Bob")
            cell("Age", 30)
            cell("Occupation", "Media")

        }
    }.render()
}