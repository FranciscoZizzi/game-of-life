(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]
            [game-of-life.test-utils :refer [expect-next-state]]))

(deftest cell-death-1
  (testing "Cell with no neighbors dies"
    (expect-next-state '[[- - -]
                         [- o -]
                         [- - -]] '[[- - -]
                                    [- - -]
                                    [- - -]])))

(deftest cell-death-2
  (testing "Cells with one nieghbor dies"
    (expect-next-state '[[- - -]
                         [o o -]
                         [- - -]] '[[- - -]
                                    [- - -]
                                    [- - -]])))

(deftest cell-survives-1
  (testing "Cells with two neighbors live"
    (expect-next-state '[[- o -]
                         [- o o]
                         [- - -]] '[[- o o]
                                    [- o o]
                                    [- - -]])))

(deftest cell-survives-2
  (testing "Cells with three neigbors live"
    (expect-next-state '[[- o o]
                         [- o o]
                         [- - -]] '[[- o o]
                                    [- o o]
                                    [- - -]])))

(deftest cell-death-3
  (testing "Any cell with more than three naighbors dies"
    (expect-next-state '[[o o - o o]
                         [o o - o o]
                         [- - o - -]
                         [o o - o o]
                         [o o - o o]] '[[o o - o o]
                                        [o - - - o]
                                        [- - - - -]
                                        [o - - - o]
                                        [o o - o o]])))

(deftest cell-birth-1
  (testing "Any dead cell with exactly three neighbors becomes a live cell"
    (expect-next-state '[[- o -]
                         [- o -]
                         [- o -]] '[[- - -]
                                    [o o o]
                                    [- - -]])))

