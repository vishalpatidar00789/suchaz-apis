import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { TraitSuchaz } from './trait-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<TraitSuchaz>;

@Injectable()
export class TraitSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/traits';

    constructor(private http: HttpClient) { }

    create(trait: TraitSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(trait);
        return this.http.post<TraitSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(trait: TraitSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(trait);
        return this.http.put<TraitSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<TraitSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<TraitSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<TraitSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<TraitSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: TraitSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<TraitSuchaz[]>): HttpResponse<TraitSuchaz[]> {
        const jsonResponse: TraitSuchaz[] = res.body;
        const body: TraitSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to TraitSuchaz.
     */
    private convertItemFromServer(trait: TraitSuchaz): TraitSuchaz {
        const copy: TraitSuchaz = Object.assign({}, trait);
        return copy;
    }

    /**
     * Convert a TraitSuchaz to a JSON which can be sent to the server.
     */
    private convert(trait: TraitSuchaz): TraitSuchaz {
        const copy: TraitSuchaz = Object.assign({}, trait);
        return copy;
    }
}
