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

@file:Suppress("Unused")

package me.thosea.kswing.states

import me.thosea.kswing.other.FullyOpen

// why can't we have nullable generics ?? :(
/**
 * Holds a never-null state value and invokes listeners when it changes
 */
@FullyOpen
class KNonnullState<T>(initialValue: T): KStateBase<T> {
	/**
	 * Read/write list of listeners that will be invoked when [value] changes or [forceNotify] is called
	 * @see listenNoarg
	 * @see useNoarg
	 * @see forceNotify
	 */
	val listeners = mutableListOf<(T) -> Unit>()

	/**
	 * State value, never null. Changing this will invoke all listeners
	 * @see listeners
	 */
	override var value: T = initialValue
		set(value) {
			field = value
			listeners.forEach { it.invoke(value) }
		}

	override val nullable = false

	/**
	 * Adds the handler to [listeners]
	 * @return the listener you passed in
	 */
	fun listen(handler: (T) -> Unit): (T) -> Unit {
		listeners += handler
		return handler
	}

	override fun listenNoarg(handler: () -> Unit): Any {
		val listener: (T) -> Unit = { handler.invoke() }
		listeners += listener
		return listener
	}

	/**
	 * Adds the handler to [listeners], then calls it once right now
	 * @return the listener you passed in
	 */
	fun use(handler: (T) -> Unit): (T) -> Unit {
		listeners += handler
		handler.invoke(this.value)
		return handler
	}

	override fun useNoarg(handler: () -> Unit): Any {
		val listener: (T) -> Unit = { handler.invoke() }
		listeners += listener
		handler.invoke()
		return listener
	}

	override fun removeListener(listener: Any) {
		@Suppress("UNCHECKED_CAST")
		listeners -= listener as (T) -> Unit
	}

	override fun forceNotify() {
		listeners.forEach { it.invoke(value) }
	}
}