import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { OccassionSuchaz } from './occassion-suchaz.model';
import { OccassionSuchazService } from './occassion-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-occassion-suchaz',
    templateUrl: './occassion-suchaz.component.html'
})
export class OccassionSuchazComponent implements OnInit, OnDestroy {
occassions: OccassionSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private occassionService: OccassionSuchazService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.occassionService.query().subscribe(
            (res: HttpResponse<OccassionSuchaz[]>) => {
                this.occassions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInOccassions();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: OccassionSuchaz) {
        return item.id;
    }
    registerChangeInOccassions() {
        this.eventSubscriber = this.eventManager.subscribe('occassionListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
