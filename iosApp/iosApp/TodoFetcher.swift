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

class TodoData: CommonData<TodoListHolder>, ObservableObject {
    
    @Published var todoList = [Todo]()
    @Published var isLoading = false
    @Published var error: KotlinThrowable? 
    
    override func onSuccess(data: TodoListHolder?) {
        todoList = data?.todos ?? []
    }
    
    override func onLoading(isLoading: Bool) {
        self.isLoading = isLoading
    }
    
    override func onError(error: KotlinThrowable) {
        self.error = error
    }
}
