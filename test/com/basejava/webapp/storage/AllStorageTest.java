package com.basejava.webapp.storage;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ArrayStorageTest.class, ListStorageTest.class, MapResumeStorageTest.class, MapUuidStorageTest.class, SortedArrayStorageTest.class, PathStorageTest.class, FileStorageTest.class})
class AllStorageTest {

}
