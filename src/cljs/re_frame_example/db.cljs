(ns re-frame-tutorial.db)

(def default-db
  {:todos {1 {:id 1 :title "豚肉を買う" :done false}
           2 {:id 2 :title "玉ねぎを買う" :done false}}})
