import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { CategoryImageSuchaz } from './category-image-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<CategoryImageSuchaz>;

@Injectable()
export class CategoryImageSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/category-images';

    constructor(private http: HttpClient) { }

    create(categoryImage: CategoryImageSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(categoryImage);
        return this.http.post<CategoryImageSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(categoryImage: CategoryImageSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(categoryImage);
        return this.http.put<CategoryImageSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<CategoryImageSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<CategoryImageSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<CategoryImageSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CategoryImageSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: CategoryImageSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<CategoryImageSuchaz[]>): HttpResponse<CategoryImageSuchaz[]> {
        const jsonResponse: CategoryImageSuchaz[] = res.body;
        const body: CategoryImageSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to CategoryImageSuchaz.
     */
    private convertItemFromServer(categoryImage: CategoryImageSuchaz): CategoryImageSuchaz {
        const copy: CategoryImageSuchaz = Object.assign({}, categoryImage);
        return copy;
    }

    /**
     * Convert a CategoryImageSuchaz to a JSON which can be sent to the server.
     */
    private convert(categoryImage: CategoryImageSuchaz): CategoryImageSuchaz {
        const copy: CategoryImageSuchaz = Object.assign({}, categoryImage);
        return copy;
    }
}
