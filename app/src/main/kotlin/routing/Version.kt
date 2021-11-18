package routing

enum class Version {
    V0;

    override fun toString(): String = name.lowercase()
}
