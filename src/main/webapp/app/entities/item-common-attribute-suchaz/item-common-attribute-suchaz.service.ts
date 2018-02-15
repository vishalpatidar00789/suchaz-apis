import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { ItemCommonAttributeSuchaz } from './item-common-attribute-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<ItemCommonAttributeSuchaz>;

@Injectable()
export class ItemCommonAttributeSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/item-common-attributes';

    constructor(private http: HttpClient) { }

    create(itemCommonAttribute: ItemCommonAttributeSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(itemCommonAttribute);
        return this.http.post<ItemCommonAttributeSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(itemCommonAttribute: ItemCommonAttributeSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(itemCommonAttribute);
        return this.http.put<ItemCommonAttributeSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ItemCommonAttributeSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<ItemCommonAttributeSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<ItemCommonAttributeSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ItemCommonAttributeSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: ItemCommonAttributeSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<ItemCommonAttributeSuchaz[]>): HttpResponse<ItemCommonAttributeSuchaz[]> {
        const jsonResponse: ItemCommonAttributeSuchaz[] = res.body;
        const body: ItemCommonAttributeSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to ItemCommonAttributeSuchaz.
     */
    private convertItemFromServer(itemCommonAttribute: ItemCommonAttributeSuchaz): ItemCommonAttributeSuchaz {
        const copy: ItemCommonAttributeSuchaz = Object.assign({}, itemCommonAttribute);
        return copy;
    }

    /**
     * Convert a ItemCommonAttributeSuchaz to a JSON which can be sent to the server.
     */
    private convert(itemCommonAttribute: ItemCommonAttributeSuchaz): ItemCommonAttributeSuchaz {
        const copy: ItemCommonAttributeSuchaz = Object.assign({}, itemCommonAttribute);
        return copy;
    }
}
