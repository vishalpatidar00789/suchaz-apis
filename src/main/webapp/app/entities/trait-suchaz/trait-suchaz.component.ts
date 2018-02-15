import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { TraitSuchaz } from './trait-suchaz.model';
import { TraitSuchazService } from './trait-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-trait-suchaz',
    templateUrl: './trait-suchaz.component.html'
})
export class TraitSuchazComponent implements OnInit, OnDestroy {
traits: TraitSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private traitService: TraitSuchazService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.traitService.query().subscribe(
            (res: HttpResponse<TraitSuchaz[]>) => {
                this.traits = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInTraits();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: TraitSuchaz) {
        return item.id;
    }
    registerChangeInTraits() {
        this.eventSubscriber = this.eventManager.subscribe('traitListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
