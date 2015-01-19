(ns treesearch.core-test
  (:require [clojure.test :refer :all]
            [treesearch.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))

(deftest game
  (testing "should plan the cannibals and missionaries problem"
    (is
     (= 16
        (count (search-tree
                problem
                strategy))))))
