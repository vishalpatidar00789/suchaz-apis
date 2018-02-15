import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { ItemSuchaz } from './item-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<ItemSuchaz>;

@Injectable()
export class ItemSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/items';

    constructor(private http: HttpClient) { }

    create(item: ItemSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(item);
        return this.http.post<ItemSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(item: ItemSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(item);
        return this.http.put<ItemSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ItemSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<ItemSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<ItemSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ItemSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: ItemSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<ItemSuchaz[]>): HttpResponse<ItemSuchaz[]> {
        const jsonResponse: ItemSuchaz[] = res.body;
        const body: ItemSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to ItemSuchaz.
     */
    private convertItemFromServer(item: ItemSuchaz): ItemSuchaz {
        const copy: ItemSuchaz = Object.assign({}, item);
        return copy;
    }

    /**
     * Convert a ItemSuchaz to a JSON which can be sent to the server.
     */
    private convert(item: ItemSuchaz): ItemSuchaz {
        const copy: ItemSuchaz = Object.assign({}, item);
        return copy;
    }
}
