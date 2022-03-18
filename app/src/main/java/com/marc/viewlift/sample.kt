
class MySampleViewModel(
  private val repository: MovieRepository
): ViewModel()  {
  
  fun saveUserName(userName: String) {
    repository.saveUser(user)
  }
  
  fun getUser(): LiveData<String> {
    return "marc
  }
}
