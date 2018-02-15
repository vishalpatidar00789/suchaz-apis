/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { TraitSuchazDialogComponent } from '../../../../../../main/webapp/app/entities/trait-suchaz/trait-suchaz-dialog.component';
import { TraitSuchazService } from '../../../../../../main/webapp/app/entities/trait-suchaz/trait-suchaz.service';
import { TraitSuchaz } from '../../../../../../main/webapp/app/entities/trait-suchaz/trait-suchaz.model';
import { SuchAzUserSuchazService } from '../../../../../../main/webapp/app/entities/such-az-user-suchaz';

describe('Component Tests', () => {

    describe('TraitSuchaz Management Dialog Component', () => {
        let comp: TraitSuchazDialogComponent;
        let fixture: ComponentFixture<TraitSuchazDialogComponent>;
        let service: TraitSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [TraitSuchazDialogComponent],
                providers: [
                    SuchAzUserSuchazService,
                    TraitSuchazService
                ]
            })
            .overrideTemplate(TraitSuchazDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TraitSuchazDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TraitSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new TraitSuchaz(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.trait = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'traitListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new TraitSuchaz();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.trait = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'traitListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
