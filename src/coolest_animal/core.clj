(ns coolest-animal.core (:require [digest :refer [md5]]))

(defn- cool-factor [animal] (->> animal
                                 md5
                                 char-array
                                 (map int)
                                 (reduce +)))

(defn- coolest [animals] (->> animals
                              (sort-by cool-factor)
                              last))

(defn -main "Reads a supplied list of animals and chooses the coolest one." []
  (let [input-directory (System/getenv "INPUT1_STAGING_DIR")
        input-file (clojure.java.io/file input-directory "animals.txt")
        output-directory (System/getenv "OUTPUT1_STAGING_DIR")
        output-file (clojure.java.io/file output-directory "coolest-animal.txt")
        animals (-> input-file slurp (clojure.string/split #"\n"))]
    (spit output-file (coolest animals))))
