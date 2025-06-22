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

@file:Suppress("FunctionName", "Unused")

package me.thosea.kswing.listeners

import java.awt.Component
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseListener

/**
 * Adds a [MouseListener]. You need to implement the methods yourself.
 * @see MouseAdapter
 */
fun Component.kMouseListener(handler: MouseListener) {
	addMouseListener(handler)
}

/**
 * Adds a [MouseListener] that only implements [MouseListener.mouseClicked]
 */
fun Component.kMouseClickedListener(handler: (MouseEvent) -> Unit) {
	addMouseListener(object: MouseAdapter() {
		override fun mouseClicked(event: MouseEvent) {
			handler.invoke(event)
		}
	})
}

/**
 * Adds a [MouseListener] that only implements [MouseListener.mousePressed]
 */
fun Component.kMousePressedListener(handler: (MouseEvent) -> Unit) {
	addMouseListener(object: MouseAdapter() {
		override fun mousePressed(event: MouseEvent) {
			handler.invoke(event)
		}
	})
}

/**
 * Adds a [MouseListener] that only implements [MouseListener.mouseReleased]
 */
fun Component.kMouseReleasedListener(handler: (MouseEvent) -> Unit) {
	addMouseListener(object: MouseAdapter() {
		override fun mouseReleased(event: MouseEvent) {
			handler.invoke(event)
		}
	})
}

/**
 * Adds a [MouseListener] that only implements [MouseListener.mouseEntered]
 */
fun Component.kMouseEnteredListener(handler: (MouseEvent) -> Unit) {
	addMouseListener(object: MouseAdapter() {
		override fun mouseEntered(event: MouseEvent) {
			handler.invoke(event)
		}
	})
}

/**
 * Adds a [MouseListener] that only implements [MouseListener.mouseExited]
 */
fun Component.kMouseExitedListener(handler: (MouseEvent) -> Unit) {
	addMouseListener(object: MouseAdapter() {
		override fun mouseExited(event: MouseEvent) {
			handler.invoke(event)
		}
	})
}