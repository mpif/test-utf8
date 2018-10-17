/*
 * @(#)MessagingException.java	1.7 99/12/06
 *
 * Copyright 1997-1999 Sun Microsystems, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Sun Microsystems, Inc.
 * Use is subject to license terms.
 *
 */

package com.codefans.java.io;

/**
 * The base class for all exceptions thrown by the Messaging classes
 *
 * @author John Mani
 */

public class MessagingException extends Exception {

	/**
	 * The next exception in the chain.
	 *
	 * @serial
	 */
	private Exception next;

	/**
	 * Constructs a MessagingException with no detail message.
	 */
	public MessagingException() {
		super();
	}

	/**
	 * Constructs a MessagingException with the specified detail message.
	 * 
	 * @param s
	 *            the detail message
	 */
	public MessagingException(String s) {
		super(s);
	}

	/**
	 * Constructs a MessagingException with the specified Exception and detail
	 * message. The specified exception is chained to this exception.
	 * 
	 * @param s
	 *            the detail message
	 * @param e
	 *            the embedded exception
	 * @see #getNextException
	 * @see #setNextException
	 */
	public MessagingException(String s, Exception e) {
		super(s);
		next = e;
	}

	/**
	 * Get the next exception chained to this one. If the next exception is a
	 * MessagingException, the chain may extend further.
	 *
	 * @return next Exception, null if none.
	 */
	public Exception getNextException() {
		return next;
	}

	/**
	 * Add an exception to the end of the chain. If the end is
	 * <strong>not</strong> a MessagingException, this exception cannot be added
	 * to the end.
	 *
	 * @param ex
	 *            the new end of the Exception chain
	 * @return <code>true</code> if the this Exception was added,
	 *         <code>false</code> otherwise.
	 */
	public synchronized boolean setNextException(Exception ex) {
		Exception theEnd = this;
		while (theEnd instanceof MessagingException && ((MessagingException) theEnd).next != null) {
			theEnd = ((MessagingException) theEnd).next;
		}
		// If the end is a MessagingException, we can add this
		// exception to the chain.
		if (theEnd instanceof MessagingException) {
			((MessagingException) theEnd).next = ex;
			return true;
		} else
			return false;
	}

	/**
	 * Produce the message, include the message from the nested exception if
	 * there is one.
	 */
	public String getMessage() {
		if (next == null)
			return super.getMessage();
		else
			return super.getMessage() + ";\n  nested exception is: \n\t" + next.toString();
	}
}
