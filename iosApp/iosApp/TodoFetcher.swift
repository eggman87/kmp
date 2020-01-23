//
//  TodoFetcher.swift
//  iosApp
//
//  Created by Pan, Xiaoheng on 1/22/20.
//

import Foundation
import app

class TodoFetcher: ObservableObject {
    @Published var todoList = [Todo]()

    init() {
        Api().getTodoList(success: { (todoList) in
            self.todoList = todoList
        }) { (error) in
            print(error!)
        }
    }
}

class TodoData: DataHolder, ObservableObject {
    @Published var todoList = [Todo]()
    
    init() {
    
    }
  
    func setData(data: Any?) {
        todoList = data as! [Todo]
    }
}

