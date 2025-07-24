package com.delicacy.client.data;

public interface BaseFactory<T extends GenericBase> {

	public T make();
}
