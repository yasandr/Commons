package org.atlanmod.testing;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EqualsVerifierTest {

    @Test
    void test() {
        Verifier.verifyEqualsOf(String.class)
                .withArguments("aaaa")
                .andVariants("bbbb")
                .check();
    }

    @Test
    void incompatibleArgumentsLength() {
        assertThatThrownBy(() -> {
            Verifier.verifyEqualsOf(String.class)
                    .withArguments(1, "aaa")
                    .andVariants(2, "bbbb")
                    .check();
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Could not find compatible constructor or factory method");
    }

    @Test
    void differentArgumentLengths() {
        assertThatThrownBy(() -> {
            Verifier.verifyEqualsOf(String.class)
                    .withArguments(1, "aaa")
                    .andVariants("bbbb")
                    .check();
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("must have the same length");
    }

    @Test
    void sameArguments() {
        assertThatThrownBy(() -> {
            Verifier.verifyEqualsOf(String.class)
                    .withArguments("aaa")
                    .andVariants("aaa")
                    .check();
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("different elements");
    }

    @Test
    void alwaysEqualClass() {
        assertThatThrownBy(() -> {
            Verifier.verifyEqualsOf(AlwaysEqualTestData.class)
                    .withArguments(1)
                    .andVariants(2)
                    .check();
        }).isInstanceOf(AssertionError.class);

    }

    @Test
    void alwaysNotEqualClass() {
        assertThatThrownBy(() -> {
            Verifier.verifyEqualsOf(AlwaysDifferentTestData.class)
                    .withArguments(1)
                    .andVariants(2)
                    .check();
        }).isInstanceOf(AssertionError.class);
    }

    @Test
    void asymmetricEquals() {
        assertThatThrownBy(() -> {
            Verifier.verifyEqualsOf(AsymmetricEqualsTestData.class)
                    .withArguments(1)
                    .andVariants(4)
                    .check();
        }).isInstanceOf(AssertionError.class)
                .hasMessageContaining("symmetric");
    }

    @Test
    void equalsToNull() {
        assertThatThrownBy(() -> {
            Verifier.verifyEqualsOf(EqualsToNullTestData.class)
                    .withArguments(1)
                    .andVariants(4)
                    .check();
        }).isInstanceOf(AssertionError.class)
                .hasMessageContaining("null");
    }

    public static class AlwaysEqualTestData {
        public AlwaysEqualTestData(Integer i) {}
        @Override
        public boolean equals(Object obj) {
            return true;
        }
    }

    public static class AlwaysDifferentTestData {
        public AlwaysDifferentTestData(Integer i) {}
        @Override
        public boolean equals(Object obj) {
            return true;
        }
    }

    public static class AsymmetricEqualsTestData {
        static int lastId = 0;

        private int i;
        public AsymmetricEqualsTestData(Integer i) {
            lastId++;
            this.i = i + lastId;
        }
        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            return i <= ((AsymmetricEqualsTestData) obj).i;
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }

    public static class EqualsToNullTestData {
        private int i;

        public EqualsToNullTestData(Integer i) {
            this.i = i;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return true;
            if (!(o instanceof EqualsToNullTestData)) return false;
            EqualsToNullTestData that = (EqualsToNullTestData) o;
            return i == that.i;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i);
        }
    }

}

