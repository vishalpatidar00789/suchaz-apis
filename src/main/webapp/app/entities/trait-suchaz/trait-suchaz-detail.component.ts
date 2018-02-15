import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { TraitSuchaz } from './trait-suchaz.model';
import { TraitSuchazService } from './trait-suchaz.service';

@Component({
    selector: 'jhi-trait-suchaz-detail',
    templateUrl: './trait-suchaz-detail.component.html'
})
export class TraitSuchazDetailComponent implements OnInit, OnDestroy {

    trait: TraitSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private traitService: TraitSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTraits();
    }

    load(id) {
        this.traitService.find(id)
            .subscribe((traitResponse: HttpResponse<TraitSuchaz>) => {
                this.trait = traitResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTraits() {
        this.eventSubscriber = this.eventManager.subscribe(
            'traitListModification',
            (response) => this.load(this.trait.id)
        );
    }
}
