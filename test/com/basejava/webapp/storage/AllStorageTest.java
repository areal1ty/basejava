package com.basejava.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( {
        ArrayStorageTest.class,
        ListStorageTest.class,
        MapFullNameStorageTest.class,
        MapUuidStorageTest.class,
        SortedArrayStorageTest.class
})
public class AllStorageTest {
}
