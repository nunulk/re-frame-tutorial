(ns re-frame-example.events
  (:require
   [re-frame.core :as re-frame]
   [re-frame-example.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(defn- generate-next-id [todos]
  (-> todos keys last ((fnil inc 0))))

(re-frame/reg-event-db
 ::add-todo
 (fn [db [event title]]
   (let [id (generate-next-id (:todos db))]
     (update db :todos #(assoc % id {:id id :title title})))))

(re-frame/reg-event-db
 ::toggle-todo
 (fn [db [event id]]
   (update-in db [:todos id :done] not)))

(re-frame/reg-event-db
 ::delete-todo
 (fn [db [event id]]
   (update db :todos dissoc id)))
