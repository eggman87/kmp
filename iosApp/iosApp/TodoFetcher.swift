//
//  TodoFetcher.swift
//  iosApp
//
//  Created by Pan, Xiaoheng on 1/22/20.
//

import Foundation
import app

class TodoData: CommonData<TodoListHolder>, ObservableObject {
    
    @Published var todoList = [Todo]()
    @Published var isLoading = false
    @Published var error: KotlinThrowable?
    @Published var hasError = false
    
    override func onSuccess(data: TodoListHolder?) {
        todoList = data?.todos ?? []
    }
    
    override func onLoading(isLoading: Bool) {
        self.isLoading = isLoading
    }
    
    override func onError(error: KotlinThrowable) {
        self.error = error
        self.hasError = true
    }
}
