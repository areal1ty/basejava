package com.basejava.webapp.storage;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        ArrayStorageTest.class,
        ListStorageTest.class,
        MapFullNameStorageTest.class,
        MapUuidStorageTest.class,
        SortedArrayStorageTest.class
})

class AllStorageTest {
}
