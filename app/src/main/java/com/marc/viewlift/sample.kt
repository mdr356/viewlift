
class MySampleViewModel(private val repository: MovieRepository): ViewModel()  {
  fun saveMovie(movie: Movie) {
    repository.saveMovie(movie)
  }
}
