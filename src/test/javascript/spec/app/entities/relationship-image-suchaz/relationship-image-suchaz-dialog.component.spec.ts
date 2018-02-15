/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { RelationshipImageSuchazDialogComponent } from '../../../../../../main/webapp/app/entities/relationship-image-suchaz/relationship-image-suchaz-dialog.component';
import { RelationshipImageSuchazService } from '../../../../../../main/webapp/app/entities/relationship-image-suchaz/relationship-image-suchaz.service';
import { RelationshipImageSuchaz } from '../../../../../../main/webapp/app/entities/relationship-image-suchaz/relationship-image-suchaz.model';
import { RelationshipSuchazService } from '../../../../../../main/webapp/app/entities/relationship-suchaz';

describe('Component Tests', () => {

    describe('RelationshipImageSuchaz Management Dialog Component', () => {
        let comp: RelationshipImageSuchazDialogComponent;
        let fixture: ComponentFixture<RelationshipImageSuchazDialogComponent>;
        let service: RelationshipImageSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [RelationshipImageSuchazDialogComponent],
                providers: [
                    RelationshipSuchazService,
                    RelationshipImageSuchazService
                ]
            })
            .overrideTemplate(RelationshipImageSuchazDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RelationshipImageSuchazDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RelationshipImageSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new RelationshipImageSuchaz(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.relationshipImage = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'relationshipImageListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new RelationshipImageSuchaz();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.relationshipImage = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'relationshipImageListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
