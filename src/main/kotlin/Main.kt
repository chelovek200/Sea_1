// ЧАСТЬ 2
fun createEmptyField(size: Int = 10): Array<CharArray> {
    return Array(size) { CharArray(size) { '.' } }
}

// ЧАСТЬ 1, 2, 4
fun printField(
    field: Array<CharArray>,
    title: String,
    showShips: Boolean = true,
    debug: Boolean = false
) {
    val size = field.size
    val header = if (debug) "=== $title (debug) ===" else "=== $title ==="
    println(header)

    fun getCellChar(r: Int, c: Int): Char {
        val cell = field[r][c]
        return if (cell == '#' && !showShips) '.' else cell
    }

    if (debug) {
        print("   ")
        println("+---".repeat(size) + "+")
        for (r in 0 until size) {
            print("$r  ")
            for (c in 0 until size) {
                print("| ${getCellChar(r, c)} ")
            }
            println("|")
            print("   ")
            println("+---".repeat(size) + "+")
        }
    } else {
        print("   ")
        for (c in 0 until size) print("$c ")
        println()
        for (r in 0 until size) {
            print("$r  ")
            for (c in 0 until size) {
                print("${getCellChar(r, c)} ")
            }
            println()
        }
    }
}

// ЧАСТЬ 3
fun printBothFields(
    player: Array<CharArray>,
    enemy: Array<CharArray>,
    showEnemyShips: Boolean = false
) {
    if (player.size != enemy.size) {
        println("Ошибка: размеры полей не совпадают! (${player.size} и ${enemy.size})")
        return
    }

    val size = player.size
    println("=== Ваше поле ===".padEnd(size * 2 + 12) + "=== Поле противника ===")

    print("   ")
    for (c in 0 until size) print("$c ")
    print("    ")
    print("   ")
    for (c in 0 until size) print("$c ")
    println()

    for (r in 0 until size) {
        print("$r  ")
        for (c in 0 until size) print("${player[r][c]} ")
        print("    ")
        print("$r  ")
        for (c in 0 until size) {
            val cell = enemy[r][c]
            if (cell == '#' && !showEnemyShips) print(". ") else print("$cell ")
        }
        println()
    }
}

// ЧАСТЬ 5
fun printFields(
    fields: Array<Array<CharArray>>,
    titles: Array<String>,
    showShips: BooleanArray
) {
    if (fields.size != titles.size || fields.size != showShips.size) {
        println("Ошибка: количество полей, заголовков и флагов не совпадает!")
        return
    }
    if (fields.isEmpty()) return

    val size = fields[0].size
    for (i in 1 until fields.size) {
        if (fields[i].size != size) {
            println("Ошибка: поля имеют разный размер! (${fields[0].size} и ${fields[i].size})")
            return
        }
    }

    val fieldWidth = 3 + size * 2 + 2

    for (i in fields.indices) {
        val title = titles[i]
        val padding = (fieldWidth - title.length) / 2
        print(" ".repeat(maxOf(0, padding)) + title)
        print(" ".repeat(maxOf(0, fieldWidth - title.length - padding)))
    }
    println()

    for (i in fields.indices) {
        print("   ")
        for (c in 0 until size) print("$c ")
        print("  ")
    }
    println()

    for (r in 0 until size) {
        for (i in fields.indices) {
            print("$r  ")
            for (c in 0 until size) {
                val cell = fields[i][r][c]
                if (cell == '#' && !showShips[i]) print(". ") else print("$cell ")
            }
            print("  ")
        }
        println()
    }
}

// MAIN
fun main() {
    // ЧАСТЬ 2
    println("ЧАСТЬ 2")
    printField(createEmptyField(8), "Маленькое поле 8×8")
    println()
    printField(createEmptyField(12), "Большое поле 12×12")
    println()
    printField(createEmptyField(), "Поле по умолчанию 10×10")
    println()

    // ЧАСТЬ 1
    println("ЧАСТЬ 1")
    val playerField = createEmptyField()
    playerField[3][2] = '#'
    playerField[3][3] = '#'
    playerField[3][4] = '#'
    playerField[3][5] = '#'

    val enemyField = createEmptyField()
    enemyField[5][7] = '#'
    enemyField[6][7] = '#'
    enemyField[7][7] = '#'
    enemyField[5][5] = 'X'
    enemyField[5][6] = 'O'

    printField(playerField, "Ваше поле")
    println()
    printField(enemyField, "Поле противника", showShips = false)
    println()

    // ЧАСТЬ 3
    println("ЧАСТЬ 3")
    printBothFields(playerField, enemyField)
    println()

    // ЧАСТЬ 4
    println("ЧАСТЬ 4")
    printField(playerField, "Ваше поле", debug = true)
    println()

    // ЧАСТЬ 5
    println("ЧАСТЬ 5")
    val thirdField = createEmptyField()
    thirdField[0][0] = 'O'
    thirdField[1][1] = 'X'

    printFields(
        arrayOf(playerField, enemyField, thirdField),
        arrayOf("Игрок", "Враг", "Третье"),
        booleanArrayOf(true, false, false)
    )
}