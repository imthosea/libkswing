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
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS �AS IS�
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

@file:Suppress("FunctionName", "Unused")

package me.thosea.kswing.listeners

import javax.swing.JComponent
import javax.swing.event.AncestorEvent
import javax.swing.event.AncestorListener

/**
 * Adds an [AncestorListener]. You need to implement the methods yourself.
 */
// no adapter, again...
fun JComponent.kAncestorAddedListener(handler: AncestorListener) {
	addAncestorListener(handler)
}

/**
 * Adds an [AncestorListener] that only implements [AncestorListener.ancestorAdded]
 */
fun JComponent.kAncestorAddedListener(handler: (AncestorEvent) -> Unit) {
	addAncestorListener(object: AncestorListener {
		override fun ancestorAdded(event: AncestorEvent) {
			handler.invoke(event)
		}

		override fun ancestorRemoved(event: AncestorEvent) {}
		override fun ancestorMoved(event: AncestorEvent) {}
	})
}

/**
 * Adds an [AncestorListener] that only implements [AncestorListener.ancestorRemoved]
 */
fun JComponent.kAncestorRemovedListener(handler: (AncestorEvent) -> Unit) {
	addAncestorListener(object: AncestorListener {
		override fun ancestorAdded(event: AncestorEvent) {}
		override fun ancestorRemoved(event: AncestorEvent) {
			handler.invoke(event)
		}

		override fun ancestorMoved(event: AncestorEvent) {}
	})
}

/**
 * Adds an [AncestorListener] that only implements [AncestorListener.ancestorMoved]
 */
fun JComponent.kAncestorMovedListener(handler: (AncestorEvent) -> Unit) {
	addAncestorListener(object: AncestorListener {
		override fun ancestorAdded(event: AncestorEvent) {}
		override fun ancestorRemoved(event: AncestorEvent) {}
		override fun ancestorMoved(event: AncestorEvent) {
			handler.invoke(event)
		}
	})
}