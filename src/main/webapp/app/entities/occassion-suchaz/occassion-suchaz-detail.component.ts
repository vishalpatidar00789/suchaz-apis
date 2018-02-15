import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { OccassionSuchaz } from './occassion-suchaz.model';
import { OccassionSuchazService } from './occassion-suchaz.service';

@Component({
    selector: 'jhi-occassion-suchaz-detail',
    templateUrl: './occassion-suchaz-detail.component.html'
})
export class OccassionSuchazDetailComponent implements OnInit, OnDestroy {

    occassion: OccassionSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private occassionService: OccassionSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInOccassions();
    }

    load(id) {
        this.occassionService.find(id)
            .subscribe((occassionResponse: HttpResponse<OccassionSuchaz>) => {
                this.occassion = occassionResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInOccassions() {
        this.eventSubscriber = this.eventManager.subscribe(
            'occassionListModification',
            (response) => this.load(this.occassion.id)
        );
    }
}
