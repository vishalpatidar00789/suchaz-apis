import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { CategorySuchaz } from './category-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<CategorySuchaz>;

@Injectable()
export class CategorySuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/categories';

    constructor(private http: HttpClient) { }

    create(category: CategorySuchaz): Observable<EntityResponseType> {
        const copy = this.convert(category);
        return this.http.post<CategorySuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(category: CategorySuchaz): Observable<EntityResponseType> {
        const copy = this.convert(category);
        return this.http.put<CategorySuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<CategorySuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<CategorySuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<CategorySuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CategorySuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: CategorySuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<CategorySuchaz[]>): HttpResponse<CategorySuchaz[]> {
        const jsonResponse: CategorySuchaz[] = res.body;
        const body: CategorySuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to CategorySuchaz.
     */
    private convertItemFromServer(category: CategorySuchaz): CategorySuchaz {
        const copy: CategorySuchaz = Object.assign({}, category);
        return copy;
    }

    /**
     * Convert a CategorySuchaz to a JSON which can be sent to the server.
     */
    private convert(category: CategorySuchaz): CategorySuchaz {
        const copy: CategorySuchaz = Object.assign({}, category);
        return copy;
    }
}
