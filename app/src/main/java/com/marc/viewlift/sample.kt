
class MySampleViewModel(
  private val repository: MovieRepository
): ViewModel()  {
  fun saveUser(user: User) {
    repository.saveUser(user)
  }
  
  fun getUsers(): LiveData<List<User>> {
    return users
  }
}
