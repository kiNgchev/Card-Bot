package models

enum class Colors(private val color: Int) {
    RED(0xDC143C),
    DANGER(0xff0900),
    GREEN(0x6BFF74),
    PURPLE(0x7567ff),
    YELLOW(0xffb800);

    fun getColor(): Int {
        return color;
    }
}