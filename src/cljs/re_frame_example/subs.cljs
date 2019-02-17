(ns re-frame-example.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::todos
 (fn [db]
   (-> db :todos vals)))
