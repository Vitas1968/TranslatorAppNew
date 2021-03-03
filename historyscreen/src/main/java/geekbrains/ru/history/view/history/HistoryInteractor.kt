package geekbrains.ru.history.view.history

import geekbrains.ru.core.viewmodel.Interactor
import geekbrains.ru.model.data.DataModel
import geekbrains.ru.model.data.dto.SearchResultDto
import geekbrains.ru.repository.Repository
import geekbrains.ru.repository.RepositoryLocal
import geekbrains.ru.translator.utils.mapSearchResultToResult

class HistoryInteractor(
    private val repositoryRemote: Repository<List<SearchResultDto>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResultDto>>
) : Interactor<DataModel> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): DataModel {
        return DataModel.Success(
            mapSearchResultToResult(
                if (fromRemoteSource) {
                    repositoryRemote
                } else {
                    repositoryLocal
                }.getData(word)
            )
        )
    }
}
