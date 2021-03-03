package geekbrains.ru.translator.view.main

import geekbrains.ru.core.viewmodel.Interactor
import geekbrains.ru.model.data.DataModel
import geekbrains.ru.model.data.dto.SearchResultDto
import geekbrains.ru.repository.Repository
import geekbrains.ru.repository.RepositoryLocal
import geekbrains.ru.translator.utils.mapSearchResultToResult

class MainInteractor(
    private val repositoryRemote: Repository<List<SearchResultDto>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResultDto>>
) : Interactor<DataModel> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): DataModel {
        val dataModel: DataModel
        if (fromRemoteSource) {
            dataModel = DataModel.Success(mapSearchResultToResult(repositoryRemote.getData(word)))
            repositoryLocal.saveToDB(dataModel)
        } else {
            dataModel = DataModel.Success(mapSearchResultToResult(repositoryLocal.getData(word)))
        }
        return dataModel
    }
}
