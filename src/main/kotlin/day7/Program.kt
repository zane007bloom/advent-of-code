package day7

data class Program(val name: String, var weight: Int, var totalWeight: Int, var children: MutableList<Program?>?) {

    fun getTotal(): Int {
        if (totalWeight != -1) {
            return totalWeight
        }
        if (children == null || children!!.isEmpty()) {
            return weight
        }
        var sum = weight
        for (child in children!!) {
            sum += child?.getTotal() ?: 0
        }
        totalWeight = sum
        return sum
    }

    fun getUnbalancedWeight(diff: Int): Int {
        if (children == null || children!!.isEmpty()) {
            return this.getTotal() + diff
        }

        val counts: MutableMap<Int, Int> = mutableMapOf()
        for (child in children!!) {
            if (!counts.containsKey(child?.getTotal())) {
                counts.put(child?.getTotal()!!, 0)
            }
            counts[child!!.getTotal()] = counts[child.getTotal()]!! + 1
        }

        if (counts.size == 1) {
            return this.weight + diff
        }

        var oddBalance = 0
        var normalBalance = 0
        for (entry in counts) {
            if (entry.value == 1) {
                oddBalance = entry.key
            } else {
                normalBalance = entry.key
            }
        }

        val diff = normalBalance - oddBalance

        var oddChild: Program? = null
        for (child in children!!) {
            if (child!!.getTotal() == oddBalance) {
                oddChild = child
            }
        }

        return oddChild!!.getUnbalancedWeight(diff)
    }
}