package com.thedeadpixelsociety.twodee

/**
 * A collection of predicates.
 */
class PredicateGroup<T> : Predicate<T> {
    private val inner = gdxArray<Predicate<T>>()

    /**
     * Invokes the predicate group for the specified item.
     * @param item The item to test each predicate in the group against.
     * @return true if all predicates in the group return true.
     */
    override fun invoke(item: T): Boolean {
        return inner.all { it.invoke(item) }
    }

    /**
     * Invokes the predicate group for the specified item.
     * @param item The item to test each predicate in the group against.
     * @return true if any predicates in the group return true.
     */
    fun any(item: T) = inner.any { it.invoke(item) }

    /**
     * Invokes the predicate group for the specified item.
     * @param item The item to test each predicate in the group against.
     * @return true if none of the predicates in the group return true.
     */
    fun none(item: T) = inner.none { it.invoke(item) }

    /**
     * Adds the specified predicate to the group.
     * @param predicate The predicate.
     */
    fun add(predicate: Predicate<T>) {
        inner.add(predicate)
    }

    /**
     * Removes the specified predicate to the group.
     * @param predicate The predicate.
     */
    fun remove(predicate: Predicate<T>) {
        inner.removeValue(predicate, true)
    }

    /**
     * Clears the predicate group.
     */
    fun clear() {
        inner.clear()
    }
}