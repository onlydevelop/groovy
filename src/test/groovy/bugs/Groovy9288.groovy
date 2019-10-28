/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package groovy.bugs

import groovy.test.NotYetImplemented
import org.junit.Test

final class Groovy9288 {

    private final GroovyShell shell = new GroovyShell()

    @Test
    void 'test accessing a protected super class field inside a closure - same package'() {
        shell.evaluate '''
            package a

            class A {
                protected String superField = 'works'
            }

            class B extends A {
                @groovy.transform.CompileStatic
                def test() {
                    'something'.with {
                        return superField
                    }
                }
            }

            def obj = new B()
            assert obj.test() == "works"
        '''
    }

    @Test
    void 'test accessing a protected super class field inside a closure - diff package'() {
        shell.evaluate '''
            package a

            class A {
                protected String superField = 'works'
            }

            assert true
        '''

        shell.evaluate '''
            package b

            class B extends a.A {
                @groovy.transform.CompileStatic
                def test() {
                    'something'.with {
                        return superField
                    }
                }
            }

            def obj = new B()
            assert obj.test() == "works"
        '''
    }

    @Test
    void 'test accessing a protected super class field inside a closure - same package, it qualifier'() {
        shell.evaluate '''
            package a

            class A {
                protected String superField = 'works'
            }

            class B extends A {
                @groovy.transform.CompileStatic
                def test() {
                    with {
                        return it.superField
                    }
                }
            }

            def obj = new B()
            assert obj.test() == "works"
        '''
    }

    @Test @NotYetImplemented // GROOVY-9292
    void 'test accessing a protected super class field inside a closure - diff package, it qualifier'() {
        shell.evaluate '''
            package a

            class A {
                protected String superField = 'works'
            }

            assert true
        '''

        shell.evaluate '''
            package b

            class B extends a.A {
                @groovy.transform.CompileStatic
                def test() {
                    with {
                        return it.superField
                    }
                }
            }

            def obj = new B()
            assert obj.test() == "works"
        '''
    }

    @Test
    void 'test accessing a protected super class field inside a closure - same package, this qualifier'() {
        shell.evaluate '''
            package a

            class A {
                protected String superField = 'works'
            }

            class B extends A {
                @groovy.transform.CompileStatic
                def test() {
                    'something'.with {
                        return this.superField
                    }
                }
            }

            def obj = new B()
            assert obj.test() == "works"
        '''
    }

    @Test
    void 'test accessing a protected super class field inside a closure - diff package, this qualifier'() {
        shell.evaluate '''
            package a

            class A {
                protected String superField = 'works'
            }

            assert true
        '''

        shell.evaluate '''
            package b

            class B extends a.A {
                @groovy.transform.CompileStatic
                def test() {
                    'something'.with {
                        return this.superField
                    }
                }
            }

            def obj = new B()
            assert obj.test() == "works"
        '''
    }

    @Test
    void 'test accessing a protected super class field inside a closure - same package, owner qualifier'() {
        shell.evaluate '''
            package a

            class A {
                protected String superField = 'works'
            }

            class B extends A {
                @groovy.transform.CompileStatic
                def test() {
                    'something'.with {
                        return owner.superField
                    }
                }
            }

            def obj = new B()
            assert obj.test() == "works"
        '''
    }

    @Test @NotYetImplemented // GROOVY-9292
    void 'test accessing a protected super class field inside a closure - diff package, owner qualifier'() {
        shell.evaluate '''
            package a

            class A {
                protected String superField = 'works'
            }

            assert true
        '''

        shell.evaluate '''
            package b

            class B extends a.A {
                @groovy.transform.CompileStatic
                def test() {
                    'something'.with {
                        return owner.superField
                    }
                }
            }

            def obj = new B()
            assert obj.test() == "works"
        '''
    }

    @Test
    void 'test accessing a protected super class field inside a closure - same package, delegate qualifier'() {
        shell.evaluate '''
            package a

            class A {
                protected String superField = 'works'
            }

            class B extends A {
                @groovy.transform.CompileStatic
                def test() {
                    with {
                        return delegate.superField
                    }
                }
            }

            def obj = new B()
            assert obj.test() == "works"
        '''
    }

    @Test @NotYetImplemented // GROOVY-9292
    void 'test accessing a protected super class field inside a closure - diff package, delegate qualifier'() {
        shell.evaluate '''
            package a

            class A {
                protected String superField = 'works'
            }

            assert true
        '''

        shell.evaluate '''
            package b

            class B extends a.A {
                @groovy.transform.CompileStatic
                def test() {
                    with {
                        return delegate.superField
                    }
                }
            }

            def obj = new B()
            assert obj.test() == "works"
        '''
    }

    @Test
    void 'test accessing a protected super class field inside a closure - same package, thisObject qualifier'() {
        shell.evaluate '''
            package a

            class A {
                protected String superField = 'works'
            }

            class B extends A {
                @groovy.transform.CompileStatic
                def test() {
                    'something'.with {
                        return thisObject.superField
                    }
                }
            }

            def obj = new B()
            assert obj.test() == "works"
        '''
    }

    @Test @NotYetImplemented // GROOVY-9292
    void 'test accessing a protected super class field inside a closure - diff package, thisObject qualifier'() {
        shell.evaluate '''
            package a

            class A {
                protected String superField = 'works'
            }

            assert true
        '''

        shell.evaluate '''
            package b

            class B extends a.A {
                @groovy.transform.CompileStatic
                def test() {
                    'something'.with {
                        return thisObject.superField
                    }
                }
            }

            def obj = new B()
            assert obj.test() == "works"
        '''
    }
}
