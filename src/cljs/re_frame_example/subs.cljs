(ns re-frame-example.subs
  (:require
   [re-frame.core :as rf]))

(rf/reg-sub
 ::todos
 (fn [db]
   (-> db :todos vals)))
