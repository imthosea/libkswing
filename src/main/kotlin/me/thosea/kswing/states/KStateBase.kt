/*
 * Copyright 2025 Thosea (github.com/imthosea)
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS “AS IS”
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package me.thosea.kswing.states

/**
 * Common interface for [KState] and [KNonnullState].
 *
 * Because of Kotlin quirks, you can only add no-arg listeners here.
 */
sealed interface KStateBase<T> {
	/**
	 * State value, may be null depending on state implementation
	 * @see nullable
	 */
	val value: T?

	/**
	 * Whether [value] can be null
	 */
	val nullable: Boolean

	/**
	 * Adds the handler to the listeners list that's invoked when [value] changes
	 * @return the listener you passed in, converted to the correct type (use with [removeListener])
	 */
	fun listenNoarg(handler: () -> Unit): Any

	/**
	 * Adds the handler to the listeners list, then calls it once right now
	 * @return the listener you passed in, converted to the correct type (use with [removeListener])
	 */
	fun useNoarg(handler: () -> Unit): Any

	/**
	 * Removes the specified listener. If not the right type, may throw an exception.
	 */
	fun removeListener(listener: Any)

	/**
	 * Notify listeners using the current [value]
	 */
	fun forceNotify()
}