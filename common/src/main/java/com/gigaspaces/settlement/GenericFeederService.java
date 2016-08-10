package com.gigaspaces.settlement;

public interface GenericFeederService {
	void stopFeeder() throws Exception;
	void startFeeder(int intervalInMilliseconds) throws Exception;
}
