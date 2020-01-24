//
//  ContentView.swift
//  iosApp
//
//  Created by Pan, Xiaoheng on 1/21/20.
//

import SwiftUI
import app

struct ContentView: View {
    
    @ObservedObject var todos = TodoData()
    lazy var todoViewModel = TodosViewModel.init(todoData:self.todos)
    
    init() {
        todoViewModel.loadTodos()
        TodoFetcher()
    }
    
    var body: some View {
        NavigationView {
            VStack {
                ActivityIndicator(isLoading: self.$todos.isLoading)
                List (todos.todoList, id: \.title) { todo in
                    Text("\(todo.title)")
                }
                .alert(isPresented: $todos.hasError) {
                    Alert(title: Text("Error"), message: Text("\((todos.error?.description())!)"))
                }
            }.navigationBarTitle("Todo Lists")
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
