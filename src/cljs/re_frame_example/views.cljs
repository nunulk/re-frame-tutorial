(ns re-frame-example.views
  (:require
   [re-frame.core :as re-frame]
   [re-frame-example.subs :as subs]
   ))

(defn todo-item [item]
  [:li (:title item)])

(defn todo-list [todos]
  [:ul
   (for [todo todos]
     ^{:key (:id todo)}
     [todo-item todo])])

(defn main-panel []
  (let [todos (re-frame/subscribe [::subs/todos])]
    [:div
     [:h1 "TODO"]
     [todo-list @todos]
     ]))
