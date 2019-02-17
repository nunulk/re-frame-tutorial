(ns re-frame-example.views
  (:require
   [clojure.string :as str]
   [re-frame.core :as re-frame]
   [re-frame-example.subs :as subs]
   [re-frame-example.events :as events]
   [reagent.core :as r]
   ))

(defn todo-create []
  (let [val (r/atom "")]
    (fn []
      [:input {:type "text"
               :value @val
               :on-change #(reset! val (-> % .-target .-value))
               :on-key-down #(when (= (.-which %) 13)
                               (let [title (-> @val str/trim)]
                                 (when (seq title)
                                   (re-frame/dispatch [::events/add-todo title]))
                                 (reset! val "")))}])))

(defn todo-item [{:keys [id done] :as item}]
  [:li
   [:input {:type "checkbox"
            :class "toggle"
            :checked (and done "checked")
            :on-change #(re-frame/dispatch [::events/toggle-todo id])}]
   [:span {:class (when done "done")}
    (:title item)]])

(defn todo-list [todos]
  [:ul
   (for [todo todos]
     ^{:key (:id todo)}
     [todo-item todo])])

(defn main-panel []
  (let [todos (re-frame/subscribe [::subs/todos])]
    [:div
     [:h1 "TODO"]
     [todo-create]
     [todo-list @todos]
     ]))
